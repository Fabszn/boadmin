package org.boa.db

import io.getquill.*
import io.getquill.jdbczio.Quill
import org.flywaydb.core.Flyway
import org.boa.model.User
import zio.{RLayer, Task, ULayer, ZEnvironment, ZIO, ZLayer}

import javax.sql.DataSource

object repository {

  val layer: RLayer[DataSource, BoaRepository] =
    ZLayer {
      for {
        datasource <- ZIO.service[DataSource]
      } yield BoaRepositoryService(datasource)
    }

  trait BoaRepository {
    def authentification(login: User.Login, pass: User.Password): Task[Option[User]]
  }

  final case class BoaRepositoryService(ds: DataSource) extends BoaRepository {

    import db.FTDBContext.*
    val env = ZEnvironment(ds)
    override def authentification(login: User.Login, pass: User.Password): Task[Option[User]] =
      run {
        quote {
          user.filter(u => u.login == lift(login) && u.password == lift(pass))
        }
      }.provideEnvironment(env).map(_.headOption)

  }

  object dataSource {

    val layer: ULayer[DataSource] = Quill.DataSource
      .fromPrefix("ctx")
      .tap(ds =>
        ZIO.attempt {
          Flyway
            .configure()
            .dataSource(ds.get)
            .load()
            .migrate()
        }
      )
      .orDie

  }

}
