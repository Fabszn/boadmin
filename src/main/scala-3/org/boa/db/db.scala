package org.boa.db

import io.getquill.*
import io.getquill.jdbczio.Quill
import org.flywaydb.core.Flyway
import org.boa.model.User
import zio.{ULayer, ZIO}

import javax.sql.DataSource

package object db {

  object FTDBContext extends PostgresZioJdbcContext(SnakeCase) {

    given InsertMeta[User] = insertMeta[User](_.id)

    val user = quote {
      querySchema[User](
        "T_USER",
        _.id -> "pkid_user"
      )
    }

  }

}
