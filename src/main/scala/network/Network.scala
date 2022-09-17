package network

import scala.annotation.tailrec

object Network {
  var peopleWithFriends: Map[Person, Set[Person]] = Map()

  def add(person: Person): Unit = {
    peopleWithFriends = peopleWithFriends + (person -> Set())
  }

  def remove(person: Person): Unit = {
    peopleWithFriends(person).foreach(unfriend(person, _))
    peopleWithFriends = peopleWithFriends.removed(person)
  }

  def friend(person1: Person, person2: Person): Unit = {
    peopleWithFriends = peopleWithFriends + (person1 -> (peopleWithFriends(person1) + person2))
    peopleWithFriends= peopleWithFriends + (person2 -> (peopleWithFriends(person2) + person1))
  }

  def unfriend(person1: Person, person2: Person): Unit = {
    peopleWithFriends = peopleWithFriends + (person1 -> (peopleWithFriends(person1) - person2))
    peopleWithFriends = peopleWithFriends + (person2 -> (peopleWithFriends(person2) - person1))
  }

  def numberOfFriends(person: Person): Int = {
    if (!peopleWithFriends.contains(person)) 0
    else peopleWithFriends(person).size
  }

  def mostPopular: Person = {
    peopleWithFriends.maxBy(_._2.size)._1
  }

  def hasNoFriends: Int = peopleWithFriends.count(_._2.isEmpty)

  def hasConnection(person1: Person, person2: Person): Boolean = {
    @tailrec
    def dfs(target: Person, consideredPeople: Set[Person], potentialPeople: Set[Person]): Boolean = {
      if (potentialPeople.isEmpty) false
      else {
        if (target == potentialPeople.head) true
        else if (consideredPeople.contains(potentialPeople.head)) {
          dfs(target, consideredPeople, potentialPeople.tail)
        } else {
          dfs(target,
            consideredPeople + potentialPeople.head,
            potentialPeople ++ peopleWithFriends(potentialPeople.head))
        }
      }
    }
    dfs(person1, Set(), Set(person2))
  }
}
