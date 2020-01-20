package ca.jent.recursion

/**
 * Extension functions names that are often used in many programming languages or in literature: head and tail
 */
fun <A> List<A>.head(): A =
    if (this.isEmpty())
        throw IllegalArgumentException("There is no head")
    else
        this[0]

fun <A> List<A>.tail(): List<A> =
    if (this.isEmpty())
        throw IllegalArgumentException("There is no tail")
    else
        this.drop(1)


/**
 * Loop abstraction
 * Loop through each list element and apply the function f starting with the identity.  Essentially, reduce
 * all the list element of type A to a computed value of type B (A and B can be of same type or not)
 */
fun <A,B> foldLeft(
    list: List<A>,
    identity: B,
    f: (A,B) -> B
): B {
    tailrec fun foldLeft(list: List<A>, acc: B): B =
        if (list.isEmpty())
            acc
        else
            foldLeft(list.tail(), f(list.head(), acc))

    return foldLeft(list, identity)
}

/**
 * Loop abstraction
 * Same as foldLeft function above except it is specialized to handle only one type.
 * This is mostly used to make the intent clearer.
 */
fun <A> reduce(list: List<A>, f: (A,A) -> A): A {
    tailrec fun reduce(list: List<A>, acc: A): A =
        if (list.isEmpty())
            acc
        else
            reduce(list.tail(), f(list.head(), acc))

    return reduce(list.tail(), list.head())
}

/**
 * Imperative looping (discouraged)
 * - mutability is discouraged as immutability is preferable (safer)
 * - can get much harder to reason about because we are "telling" how to do the computation instead of asking for
 *   a computation.
 * - Noisy: we are actually only interested in the operation which is adding all the Int element in the list.  The
 *   looping is not what interest us.
 * - No abstraction.  The operation is hardcoded
 */
fun imperativeLoopExample_1(list: List<Int>): Int {
    // simple addition of all the element
    var acc: Int = 0
    for (i in 0..list.size - 1) {
        acc += list[i]
    }
    return acc
}

/**
 * Same problem as the above imperative looping.  It does not mater which kind of imperative looping is used.
 */
fun imperativeLoopExample_2(list: List<Int>): Int {
    // simple addition of all the element
    var acc: Int = 0
    for (element in list) {
        acc += element
    }
    return acc
}

/**
 * Let's try all the functions above.
 */
fun main() {
    val list: List<Int> = listOf(1,2,3,4,5)

    println(imperativeLoopExample_1(list))  // 15
    println(imperativeLoopExample_2(list))  // 15
    println(reduce(list, { a,b -> a + b })) // 15
    println(reduce(list, { a,b -> a * b })) // 120

}
