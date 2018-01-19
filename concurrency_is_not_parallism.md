# Concurrency is not parallism

Not related to how they are executed, it's about planning

Concurrency enables parallism, "but it's not the goal, the goal is to
structure the program"

# Use case

# why do we need them

Modern computers have multiple cores, most applications are IO bounded
(web application)

What is IO, it stands for Input/Output, but I'll simplify it a bit:
It's about when CPU is idle waiting for something to finish

# Thread

What's a thread, it's to split the work so that the CPU can execute the
concurrently

## Thread variance

Green thread, Coroutines, Actor, reactor

## Nodejs, go, erlang

## Apache vs Nginx

# Scheduling

## The role of OS (or VM)

## why: CPU is limited

# Synchronization

## why: race condition (demo)

## how: locks, conditional variables

# Implementations

Stream, Executor, Thread Pool

Promise, Future

# Parallism (Process)

* difference
* How

# When it doesn't work

# recap


# QA

# links

Rob Pike's talk, Java concurrency in practice.


# ideas

compare different langauges

