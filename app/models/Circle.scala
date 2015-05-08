package models

import anorm._
import anorm.SqlParser._
import play.api.db._
import play.api.Play.current

case class Circle(id: Long, label: String)

object Circle {
  def all(): List[Circle] = DB.withConnection { implicit c =>
    SQL("select * from circles").as(circle *)
  }

  def create(label: String) {
    DB.withConnection { implicit c =>
      SQL("insert into circles (label) values ({label})").on(
        'label -> label
      ).executeUpdate()
    }
  }

  def delete(id: Long) {
    DB.withConnection { implicit c =>
      SQL("delete from circles where id = {id}").on(
        'id -> id
      ).executeUpdate()
    }
  }

  val circle = {
    get[Long]("id") ~
      get[String]("label") map {
        case id ~ label => Circle(id, label)
      }
  }

}
