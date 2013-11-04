package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import models.Task
import play.api.libs.iteratee._

object Application extends Controller {
  
  def index = Action {
    Redirect(routes.Application.tasks)
  }

  def tasks = Action {
    Ok(views.html.index(Task.all(),taskForm))
  }

  def newTask = Action{ implicit request =>
    taskForm.bindFromRequest.fold(
      errors => BadRequest(views.html.index(Task.all(),errors)),
      label => {
        Task.create(label)
        Redirect(routes.Application.tasks)
      }
    )
  }

  def deleteTask(id: Long) = Action {
    Task.delete(id)
    Redirect(routes.Application.tasks)
  }
  
  val taskForm = Form(
    "label" -> nonEmptyText
  )
  def circles = TODO
  def newCircle = TODO
  def events = TODO
  def newEvent = TODO
  def users = TODO
  def newUser = TODO
  def possibles = TODO
}

