package org.boa.services

import org.boa.model.User
import org.boa.services.authService.Authentification
import zio.*
import zio.http.*
import zio.http.codec.PathCodec.*

object httpServer {

  val app: Routes[Authentification, Response] =
    (literal("api") /
      Routes.fromIterable(Chunk(Method.POST / "login" -> handler { (req: Request) =>
        for {
          auth <- ZIO.service[Authentification]
          r    <- auth.authentification(User.Login(""), User.Password(""))
          _<- ZIO.logInfo(s"query result ${r}")
        } yield Response.ok
      }.mapError(er => Response.error(Status.InternalServerError, s"er.getMessage ${er}"))))) ++ Routes(
      Method.GET / "" -> Handler
        .fromResource("index.html")
        .addHeader(
          Header.ContentType.name,
          "text/html; charset=utf-8"
        )
    ).mapError(er => Response.error(Status.InternalServerError, er.getMessage)) @@ Middleware.serveResources(
      Path.empty
    ) @@ Middleware.debug

}
