package ru.antonivanov87

import scala.annotation.tailrec

package object jobs {

  def sortNonOpt(jobs: List[Job]): List[Job] = {
    jobs.sortBy((job: Job) => (job.weight-job.length, job.weight)).reverse
  }

  def sortOpt(jobs: List[Job]): List[Job] = {
    jobs.sortBy((job: Job) => job.weight.toDouble/job.length).reverse
  }

  def weightedCompletionTimes(jobs: List[Job]): Long = {

    @tailrec
    def weightedCompletionTimesAcc(jobs: List[Job], startTime: Int, weightedCompletionTimesSoFar: Long): Long =
      jobs match {
        case Nil => weightedCompletionTimesSoFar
        case job :: restJobs => weightedCompletionTimesAcc(
         restJobs,
         startTime + job.length,
         weightedCompletionTimesSoFar + job.weight * (startTime + job.length)
        )
      }

    weightedCompletionTimesAcc(jobs, startTime=0, weightedCompletionTimesSoFar=0)

  }

  def weightedCompletionTimesNonOpt(jobs: List[Job]): Long = {
    weightedCompletionTimes(sortNonOpt(jobs))
  }

  def weightedCompletionTimesOpt(jobs: List[Job]): Long = {
    weightedCompletionTimes(sortOpt(jobs))
  }

}
