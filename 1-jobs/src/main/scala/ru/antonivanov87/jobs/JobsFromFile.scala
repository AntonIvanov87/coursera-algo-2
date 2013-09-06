package ru.antonivanov87.jobs

import scala.io.Source

object JobsFromFile {

  def read(source: Source): List[Job] = {

    val allLines: Iterator[String] = source.getLines()
    val jobsLines: Iterator[String] = allLines drop 1
    val jobsIterator: Iterator[Job] = for (jobLine <- jobsLines) yield {
      val lineParts: Array[String] = jobLine split " "
      val weight: Int = lineParts(0).toInt
      val length: Int = lineParts(1).toInt
      Job(weight, length)
    }
    jobsIterator.toList

  }

}
