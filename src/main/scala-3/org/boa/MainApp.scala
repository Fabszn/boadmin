package org.boa

import org.boa.db.repository
import org.boa.services.authService
import org.boa.services.authService.Authentification
import zio.*
import zio.http.*

object MainApp extends ZIOAppDefault {

  override def run: ZIO[Any, Throwable, Nothing] =
    val d: Routes[Authentification, Response] = services.httpServer.app
    Server.serve(d).provide(Server.default, repository.dataSource.layer,authService.layer, repository.layer) 

}
