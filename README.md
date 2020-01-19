# KOTLIN demo: fun_with_recursion
Few simple corecursive (TCE) functions.

TCE: Tail Call End

In Kotlin, if the recursive call is the last call (TCE), you can use the keywork `tailrec` which will compile the recursion as an imperative loop.  This way, no stack overflow will occur.  Such recursion is called `corecursive` while an actual recursion (i.e. not TCE) are not protected against stack overflow.
