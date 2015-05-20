package models

import anorm.SqlParser._
import anorm._
import play.api.db.DB
import play.api.Play.current

/**
 * User
 */
case class User(id: Long, name: String, password: String)

object User {

  val userData = {
    get[Long]("id") ~
      get[String]("name") ~
      get[String]("password") map {
        case id ~ name ~ password => User(id, name, password)
      }
  }

  def all(): List[User] = DB.withConnection { implicit connection =>
    SQL("select * from user").as(userData *)
  }

  def create(user: User) = DB.withTransaction { implicit connection =>
    SQL(
      """
          insert into user
            (email, name, password) values (
    		{email}, {name}, {password}
          )
      """
    ).on(
        'name -> user.name,
        'password -> user.password
      ).executeUpdate()
  }

  def update(id: Long, user: User) = DB.withTransaction { implicit connection =>
    SQL(
      """
  			update user set email = {email}, name = {name}, password = {password}
  	        where id = {id}
      	    """
    ).on(
        'id -> id,
        'name -> user.name,
        'password -> user.password
      ).executeUpdate()
  }

  def find(id: Long): Option[User] = DB.withConnection { implicit connection =>
    SQL("select * from user where id = {id}").on(
      'id -> id
    ).as(userData.singleOpt)
  }

  def delete(id: Long) = DB.withTransaction { implicit connection =>
    SQL("delete from user where id = {id}").on(
      'id -> id
    ).executeUpdate()
  }

  def authenticate(name: String, password: String): Option[User] = {
    DB.withConnection { implicit connection =>
      SQL(
        """
    		  	select * from user where
    		  	name = {name} and password = {password}
        		  """
      ).on(
          'name -> name,
          'password -> password
        ).as(User.userData.singleOpt)
    }
  }
}
