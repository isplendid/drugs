package functional.util

import play.api.db.slick.{DatabaseConfigProvider, NamedDatabaseConfigProvider}
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.inject.{Injector, bind}

trait WithTestInjector {
  lazy val injector: Injector = new GuiceApplicationBuilder()
    .overrides(
      bind[DatabaseConfigProvider].to(new NamedDatabaseConfigProvider("test"))
    )
    .injector()
}
