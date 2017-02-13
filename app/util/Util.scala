package util

import com.google.inject.Singleton

import scala.concurrent.Future

@Singleton
class Util {

}

object Util {

  object Implicits {

    implicit class Futurable[A](a: A) {
      def future: Future[A] = Future.successful(a)
    }
  }

}
