package ru.antonivanov87.jobs

import org.scalatest.FlatSpec

class TestPackage extends FlatSpec {

  "sortNonOpt" should "sort jobs by weight-length desc" in {
    val job0: Job = Job(weight=10, length=1)
    val job1: Job = Job(weight=20, length=18)
    val job2: Job = Job(weight=30, length=29)
    val jobs: List[Job] = List(job1, job0, job2)

    val jobsSortedNonOpt: List[Job] = sortNonOpt(jobs)

    expectResult(job0)(jobsSortedNonOpt(0))
    expectResult(job1)(jobsSortedNonOpt(1))
    expectResult(job2)(jobsSortedNonOpt(2))

  }

  "sortNonOpt" should "sort jobs with equal weight-length by weight desc" in {

    val job0: Job = Job(weight=20, length=15)
    val job1: Job = Job(weight=10, length=5)
    val job2: Job = Job(weight=6, length=1)

    val jobs: List[Job] = List(job1, job0, job2)

    val jobsSortedNonOpt: List[Job] = sortNonOpt(jobs)

    expectResult(job0)(jobsSortedNonOpt(0))
    expectResult(job1)(jobsSortedNonOpt(1))
    expectResult(job2)(jobsSortedNonOpt(2))

  }

  "sortNonOpt" should "sort jobs first by weight-length desc, second by weight desc" in {

    val job0: Job = Job(weight=10, length=1)
    val job1: Job = Job(weight=11, length=3)
    val job2: Job = Job(weight=10, length=2)
    val jobs: List[Job] = List(job1, job0, job2)

    val jobsSortedNonOpt: List[Job] = sortNonOpt(jobs)

    expectResult(job0)(jobsSortedNonOpt(0))
    expectResult(job1)(jobsSortedNonOpt(1))
    expectResult(job2)(jobsSortedNonOpt(2))

  }

  "sortOpt" should "sort jobs by weight/length desc" in {

    val job0: Job = Job(weight=10, length=1)
    val job1: Job = Job(weight=5, length=2)
    val job2: Job = Job(weight=15, length=14)
    val jobs: List[Job] = List(job1, job0, job2)

    val jobsSortedOpt: List[Job] = sortOpt(jobs)

    expectResult(job0)(jobsSortedOpt(0))
    expectResult(job1)(jobsSortedOpt(1))
    expectResult(job2)(jobsSortedOpt(2))

  }

  "weightedCompletionTimes" should "return weighted completion times" in {

    val job0: Job = Job(weight=1, length=1)
    val job1: Job = Job(weight=2, length=2)
    val job2: Job = Job(weight=3, length=3)
    val jobs: List[Job] = List(job0, job1, job2)

    expectResult(1 * 1 + 2 * 3 + 3 * 6)(weightedCompletionTimes(jobs))

  }

  "weightedCompletionTimesNonOpt" should "return weighted completion times of jobs sorted non optimal" in {

    val job0: Job = Job(weight=10, length=1)
    val job1: Job = Job(weight=11, length=3)
    val job2: Job = Job(weight=10, length=2)
    val jobs: List[Job] = List(job1, job0, job2)

    expectResult(10 * 1 + 11 * 4 + 10 * 6)(weightedCompletionTimesNonOpt(jobs))

  }

  "weightedCompletionTimesOpt" should "return weighted completion times of jobs sorted optimal" in {

    val job0: Job = Job(weight=10, length=1)
    val job1: Job = Job(weight=5, length=2)
    val job2: Job = Job(weight=15, length=14)
    val jobs: List[Job] = List(job1, job0, job2)

    expectResult(10 * 1 + 5 * 3 + 15 * 17)(weightedCompletionTimesOpt(jobs))

  }

}
