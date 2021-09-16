package ru.sber.generics



// 1.
fun <T1, T2> compare(p1: Pair<T1, T2>, p2: Pair<T1, T2>): Boolean {
    if (p1.first == p2.first && p1.second == p2.second)  {
        return true
    }
    return false
}
//fun <T1 : Comparable<T1>, T2: Comparable<T2>> compareComparable(p1: Pair<T1, T2>, p2: Pair<T1, T2>): Boolean {
//    if (p1.first.compareTo(p2.first) == 0 && p1.second.compareTo(p2.second) == 0)  {
//        return true
//    }
//    return false
//}


// 2.
//fun countGreaterThan(anArray: Array<Any>, elem: Any): Int {
//    return 0
//}
fun <T : Comparable<T>> countGreaterThan(anArray: Array<T>, elem: T) = anArray.asSequence().filter { it > elem }.count()


// 3.
//class Sorter {
//    val list: MutableList
//
//    fun add(value: Any) {
//    }
//}
class Sorter<T : Comparable<T>> {
    val list: MutableList<T> = mutableListOf()

    fun add(value: T) {
        if (list.isEmpty()) {
            list.add(value)
        } else {
            for (i in 0..list.size) {
                if (i == list.size || value < list[i]) {
                    list.add(i, value)
                    break
                }
            }
        }
    }
}

// 4.
class Stack<T> {
    private class Node<T>(val prevNode: Node<T>?, val value: T?) {}

    private var top: Node<T> = Node(null, null)

    fun isEmpty(): Boolean {
        if (top.prevNode == null) {
            return true
        }
        return false
    }

    fun push(value: T) {
        top = Node(top, value)
    }

    fun pop(): T? {
        if (!isEmpty()) {
            return top.value.also {
                top = top.prevNode ?: Node(null, null)
            }
        }
        return null
    }

//    fun popThrows(): T {
//        if (!isEmpty()) {
//            return top.value!!.also { top = top.prevNode ?: Node(null, null)}
//        }
//        throw IndexOutOfBoundsException()
//    }
}