package io.getquill

import java.io.Closeable

import javax.sql.DataSource
import com.typesafe.config.Config
import io.getquill.context.jdbc.{ JdbcContext, PostgresJdbcContextBase }
import io.getquill.util.LoadConfig

class PostgresJdbcContext[+N <: NamingStrategy](val naming: N, val dataSource: DataSource with Closeable)
  extends JdbcContext[PostgresDialect, N]
  with PostgresJdbcContextBase[PostgresDialect, N] {
  override val idiom: PostgresDialect = PostgresDialect
  def this(naming: N, config: JdbcContextConfig) = this(naming, config.dataSource)
  def this(naming: N, config: Config) = this(naming, JdbcContextConfig(config))
  def this(naming: N, configPrefix: String) = this(naming, LoadConfig(configPrefix))
}
