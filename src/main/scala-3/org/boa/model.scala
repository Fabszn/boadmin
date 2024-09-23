package org.boa

object model {

  final case class User(
      id: User.Id = User.Id(-1),
      nom: User.Nom,
      prenom: User.Prenom,
      login: User.Login,
      password: User.Password,
      emailContact: Option[User.EmailContact]=Option.empty[User.EmailContact]
  )
  object User {
    final case class Id(value: Long) extends AnyVal
    final case class Nom(value: String) extends AnyVal
    final case class Prenom(value: String) extends AnyVal
    final case class Login(value: String) extends AnyVal
    final case class EmailContact(value: String) extends AnyVal
    final case class Password(value:String) extends AnyVal
  }
}
