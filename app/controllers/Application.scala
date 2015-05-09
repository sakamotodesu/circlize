package controllers

import models.{Circle, Task}
import play.api.data.Forms._
import play.api.data._
import play.api.mvc._

object Application extends Controller {

  def index = Action {
    Redirect(routes.Application.tasks())
  }

  def tasks = Action {
    Ok(views.html.index(Task.all(), taskForm))
  }

  def newTask = Action { implicit request =>
    taskForm.bindFromRequest.fold(
      errors => BadRequest(views.html.index(Task.all(), errors)),
      label => {
        Task.create(label)
        Redirect(routes.Application.tasks())
      }
    )
  }

  def deleteTask(id: Long) = Action {
    Task.delete(id)
    Redirect(routes.Application.tasks())
  }

  val taskForm = Form(
    "label" -> nonEmptyText
  )

  def circles = Action {
    Ok(views.html.circle(Circle.all(), taskForm))
  }

  def newCircle = Action { implicit request =>
    taskForm.bindFromRequest.fold(
      errors => BadRequest(views.html.circle(Circle.all(), errors)),
      label => {
        Circle.create(label)
        Redirect(routes.Application.circles())
      }
    )
  }

  def deleteCircle(id: Long) = Action {
    Circle.delete(id)
    Redirect(routes.Application.circles())
  }

  def events = TODO
  def newEvent = TODO
  def users = TODO
  def newUser = TODO
  def possibles = TODO
}

