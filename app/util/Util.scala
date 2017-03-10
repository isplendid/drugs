package util

import com.google.inject.Singleton

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._

@Singleton
class Util {

}

object Util {

  def AwaitResult[A](f: => Future[A]): A = Await.result(f, 1.seconds)

  object Implicits {

    implicit class Futurable[A](a: A) {
      def toFuture: Future[A] = Future.successful(a)
    }
  }

}
