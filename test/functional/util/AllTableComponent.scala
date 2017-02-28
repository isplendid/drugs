package functional.util

import play.api.db.slick.HasDatabaseConfigProvider
import slick.driver.JdbcProfile
import tables.{DistributorComponet, DrugComponent, IngredientComponet}

trait AllTableComponent extends HasDatabaseConfigProvider[JdbcProfile]
  with DrugComponent
  with IngredientComponet
  with DistributorComponet
