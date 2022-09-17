package network

object TestNetwork extends App {
  val maks = Person("Maks")
  val vika = Person("Vika")
  val ivan = Person("Ivan")
  Network.add(maks)
  Network.add(vika)
  Network.remove(vika)
  println(Network.peopleWithFriends)
  Network.add(vika)
  Network.add(ivan)
  Network.friend(maks, vika)
  Network.friend(maks, ivan)
  Network.friend(ivan, vika)
  Network.remove(vika)
  println(Network.peopleWithFriends)
  Network.add(vika)
  Network.unfriend(maks, vika)
  println(Network.peopleWithFriends)

  Network.friend(maks, vika)

  println(Network.numberOfFriends(vika))

  println(Network.mostPopular)
  println(Network.hasNoFriends)
  println(Network.hasConnection(maks, vika))
}
