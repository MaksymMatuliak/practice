package connection

import scala.util.Random

class Connection {
  def connect = "Connected!"

  def get(url: String): String = {
    val random = new Random(System.nanoTime())
    if(random.nextBoolean) "<html>Hello World</html>"
    else throw  new RuntimeException("Connection is interrupted")
  }
}

object Connection {
  val random = new Random(System.nanoTime())

  def apply(host: String, port: String): Option[Connection] = {
    if (random.nextBoolean()) Some(new Connection)
    else None
  }
}

object testConnection extends App {
  val config: Map[String, String] = Map(
    "host" -> "172.54.24.1",
    "port" -> "80"
  )

  val host = config.getOrElse("host", throw RuntimeException("No host"))
  val port = config.getOrElse("port", throw RuntimeException("No port"))
  println(Connection(host, port).getOrElse(throw  new RuntimeException("No connction")).connect)
}
