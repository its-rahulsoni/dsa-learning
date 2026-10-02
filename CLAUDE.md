# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Overview

Personal DSA / Java learning repo (Maven, Spring Boot 3.2.0 parent, Java 17, Lombok, Log4j2 instead of default Spring logging). It is a collection of standalone practice classes, not a cohesive application.

## Commands

- Build: `mvn clean install`
- Compile only: `mvn compile`
- Test: `mvn test`; single test: `mvn test -Dtest=ClassName#methodName` (`src/test/java` currently has no tests)
- Run a single exercise: most classes (~128 of 149) have their own `public static void main`. Run them from the IDE, or e.g. `mvn compile exec:java -Dexec.mainClass=com.dsa.learning.queues.customimpl.CustomQueueUsingArray` (exec plugin is not in the pom; it resolves by prefix on demand).
- `DSALearningApplication` is the Spring Boot entry point but is not where the exercises live.

## Structure

Everything is under `src/main/java/com/dsa/learning/`, organized by topic, one self-contained example per class:

- `java8/` — functional interfaces, `completable_future`, `streams` (parallel, infinite)
- `queues/` — core, custom array/linked-list implementations, priority queues, blocking queues
- `maths/striver`, `string/striver`, `string/dial_epam` — problem sets following Striver's sheets
- `preparation_2023/` — interview prep: `codingpatterns`, `datastructures`, `interviews`, `leetcode`
- `interview_exercises/qatar` — a multi-class flight-availability service exercise
- `design_patterns/`, `models/` (shared DTOs such as `EmployeeDTO`, `Transaction`)

New exercises should be added as new classes in the matching topic package with their own `main`, rather than wired into the Spring app.

## Git

- Commit messages start with a verb in the imperative mood.
- Work happens on topic branches merged into `main` via PRs.
