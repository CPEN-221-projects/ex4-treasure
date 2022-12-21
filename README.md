# There's Treasure Everywhere

A group of pirates, like Jack Sparrow, Davy Jones and Elizabeth Swann, has somehow landed their hands on some very valuable treasure! And now it is time to decide how this loot is divided among the pirates. Your work is to help this motley crew use software to manage their treasure. It may not be an easy task all the way through.

## Task 1: Basic Treasure Allocation

You will primarily have to implement the method `PirateManager.buildPiratesWithTreasure`. This method takes as input an array `treasures` of `Treasure` references and an array `pirateNames` of `String` references. The array of `String` references refers to the names of the pirates. These two arrays, jointly, represent the mapping of treasure to pirates. The pirate at `pirateName[i]` gets `treasures[i]`. You then have to return a `List` of `Pirate`s with each `Pirate` having the appropriate allocation of treasures.

To complete this task, you will also have to complete these methods in the `Pirate` class:

1.   the constructor
2.   `addTreasure`
3.   `getTreasureValue`
4.   `getHighestValueTreasure`

This class has one other method that features later.

**Start by looking at the provided tests first!**

## Task 2: Is the Allocation Balanced?

In this task, you have to take the allocation obtained from Task 1, for instance, and determine if the allocation is balanced with some margin for deviation. Suppose there are N pirates and the total value of **all** treasure is T, and if the allowed deviation is p%, an allocation is balanced if each pirate gets treasure (of total value) that is in the range [T/N * (100 - p)/100, T/N * (100 + p)/100]. You have to implement `PirateManager.isBalanced`, which takes the array of treasures, the list of pirates with the treasure allocations (say, from Task 1), and the permitted deviation, and determines if the allocation is balanced. (All the treasure that is up for allocation has to be allocated.)

## Task 3: Software Robustness

You will have to complete three sub-tasks:

1. Ensure that balanced allocation method (Task 2) verifies that the pirates have a balanced allocation of the actual treasure to be distributed and not some ill-gotten treasure previously not known of.
2. Implement the `getTreasures` method in the `Pirate` class, taking care to ensure that you do not accidentally allow mutation of the `Pirate` instance.
3. Ensure that `PirateManager.buildPiratesWithTreasure` throws an `IllegalArgumentException` when the appropriate precondition is not met

## Task 4: Computing Balanced Allocations

In Task 1, we assumed that an allocation of treasure to pirates was known. In this task, you are going to help Elizabeth Swann divide the treasure among Jack Sparrow, Hector Barbarossa and Davy Jones. She needs to ensure that Barabarossa and Jones get the same amount of treasure (in total value). Barbarossa and Jones will be happy as long as they get the maximum possible treasure value subject to this balance requirement. Jack Sparrow is happy-go-lucky and takes the remains, and in some cases he may get it all because the only allocation that ensures balance between the other two is to give each of them nothing!

How will she handle this difficult task? Help her manage pirate egos by implementing `PirateManager.balancedAllocation(Treasure[] treasures)`.

## Logistics

Task 4 may be  more work than the other three tasks. If you are taking more time to  complete Tasks 1, 2 and 3, then it may not be a good time investment today to work on Task 4 as well.

There is no partial credit for a  task. You get credit for a task if your implementation passes all tests  we subject your submission to. And there will be more tests than what  you will be provided with. The PPT has been broken up into different  tasks so that you can get partial credit.

**Grading**

| Work Accomplished | Grade |
| ----------------- | ----- |
| Task 1 passes all hidden tests | C |
| Tasks 1, 2 pass all hidden tests | B |
| Tasks 1, 2 and 3 pass all hidden tests | A |
| Tasks 1 through 4 pass all hidden tests | A+ |

### Submission Instructions

+ Submit your work to the Github classroom repository that was created for you.
+ **Do not alter the directory/folder structure. You should retain the structure as in this repository.**
+ Do not wait until the last minute to push your work to Github. It is a good idea to push your work at intermediate points as well. _We would recommend that you get your Git and Github workflow set up at the start._

### What Should You Implement / Guidelines

+ You should implement all the methods that are indicated with `TODO`.
+ Passing the provided tests is the minimum requirement. Use the tests to identify cases that need to be handled. Passing the provided tests is *not sufficient* to infer that your implementation is complete and that you will get full credit. Additional tests will be used to evaluate your work. The provided tests are to guide you.
+ You can implement additional helper methods if you need to but you should keep these methods `private` to the appropriate classes.
+ You do not need to implement new classes.
+ You can use additional **standard** Java libraries by importing them.
+ Do not throw new exceptions unless the specification for the method permits exceptions.


## Honour Code

By submitting your work to GitHub you agree to the following:

+ You did not consult with any other person for the purpose of completing this activity.
+ You did not aid any other person in the class in completing their activity.
+ If you consulted any external sources, such as resources available on the World Wide Web, in completing the examination then you have cited the source. (You do not need to cite class notes or Sun/Oracle Java documentation.)
+ You are not aware of any infractions of the honour code for this examination.

## Answers to FAQs

* **Can I consult Java documentation and other Internet-based sources?**

    Yes, you can. The point of this test is not to demonstrate mastery over syntax but that you can solve a problem in a    reasonable amount of time with reasonable resources.

    *If you find useful information online outside the official Java documentation and the course material, you must cite the source. You should do so by adding comments in your source code.*

    Naturally you are expected to adhere to all of the course and UBC policies on academic integrity.

* **Isn't 75 minutes insufficient time to produce a working implementation?**

    The questions are straightforward, and these are not very different from what one might sometimes encounter on a job interview (for example). The difference is that you get less time during an interview (10-15 minutes) with no access to additional resources. So the time allotted is reasonable in that regard and I am expecting that everyone will be able to clear this bar. The goal is that it is possible to say, at a minimal level, what everyone who completes this course can achieve.

* **Why am I not guaranteed full credit if my implementation passes all the provided tests?**

    It is easy to develop an implementation that passes the provided tests and not much else. A good-faith implementation that passes all the provided tests is very likely to pass other tests too.
