Concurrency is not parallism
============================

Not related to how they are executed, it's about planning

Concurrency enables parallism, "but it's not the goal"[1]

Why do we need them
-------------------

Modern computers have multiple cores, most applications are IO bounded
(web application)

What is IO, it stands for Input/Output, but I'll simplify it a bit:
It's about when CPU is idle waiting for something to finish

Thread
------

split the work into small tasks so that the CPU can execute them concurrently

Variance:

Green thread, Coroutines, Actor, callback

Scheduling
==========

why: CPU number is limited
-------------------

The role of OS (or VM)
----------------------

Preemptive vs Cooperative
-------------------------

Synchronization
=================

why: race condition (demo)
--------------------------

how: mutex (lock)
---------------------------------

Abstractions
============

Stream, Executor, Thread Pool

Promise, Future

Process
=======

* Difference

When it doesn't work
====================

recap
=====


Q & A
=====

# links

[1] Rob Pike's talk
[2] Java concurrency in practice.
