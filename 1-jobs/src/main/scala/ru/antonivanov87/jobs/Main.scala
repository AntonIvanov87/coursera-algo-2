package ru.antonivanov87.jobs

import scala.io.Source

object Main {

  def main(args: Array[String]) {

    val source: Source = Source fromURL (getClass getResource "/jobs.txt")
    val jobs: List[Job] = JobsFromFile.read(source)
    println("Weighted completion times, non-opt ordering: " + weightedCompletionTimesNonOpt(jobs))
    println("Weighted completion times, opt ordering    : " + weightedCompletionTimesOpt(jobs))

  }

}
