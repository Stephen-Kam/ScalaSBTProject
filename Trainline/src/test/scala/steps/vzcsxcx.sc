def switchExample(i : Int) = i  match {
  case 1 => println("Printed One")
  case 2 => println("Printed Two")
  case 3 => println("Printed Three")
}

//def sum(f: Int => Int, a: Int, b: Int): Int =
  //if (a > b) 0
  //else f(a) + sum(f, a + 1, b)

def id(x: Int): Int = x
def cube(x: Int) : Int = x * x * x


  def sum(f: Int => Int, a: Int, b: Int) = {
    def loop(a: Int, acc: Int): Int =
      if (a > b) acc
      else loop(a + 1, f(a) + acc)
    loop(a, 0)
  }
  sum(x => x * x,  3,  5)

def product(f: Int => Int)(a: Int, b: Int): Int =
  if (a > b) 1
  else f(a) * product(f)(a + 1,  b)
  product(x => x * x)(3,  4)

def fact(n: Int) = product(x => x)(1, n)
fact(5)

class Rational(x: Int, y: Int) {
  def numer = x
  def denom = y

  def add(that: Rational) =
    new Rational(
        numer * that.denom + that.numer * denom,
      denom * that.denom
    )

  def neg: Rational = new Rational(-numer, denom)

  def sub(that: Rational) = add(that.neg)

  override def toString = numer + "/" + denom
}

val x = new Rational(1, 3)
val y = new Rational(5, 7)
val z = new Rational(3, 2)
x.numer
x.denom
x.sub(y).sub(z)
x.neg

class Dog {

  override def toString: String = {
    "A dog"
  }
}

val d = new Dog
println(d.toString)





