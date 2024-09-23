package org.boa.services

import org.boa.db.repository.BoaRepository
import org.boa.model.User
import zio.{Task, URLayer, ZIO, ZLayer}

object authService {

  val layer: URLayer[BoaRepository, AuthentificationService] =
    ZLayer(for {
      auth <- ZIO.service[BoaRepository]
    } yield AuthentificationService(auth))

  trait Authentification {
    def authentification(login: User.Login, mdp: User.Password): Task[Boolean]
  }

  final case class AuthentificationService(repository: BoaRepository) extends Authentification {
    override def authentification(login: User.Login, mdp: User.Password): Task[Boolean] =
      repository.authentification(login, mdp).map {
        case None    => false
        case Some(_) => true
      }
  }

  def authentification(login: User.Login, mdp: User.Password): ZIO[Authentification, Throwable, Boolean] =
    ZIO.serviceWithZIO[Authentification](_.authentification(login, mdp))

}
