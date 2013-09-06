package ru.antonivanov87.jobs

import scala.io.Source
import org.scalatest.FlatSpec

class TestJobsFromFile extends FlatSpec {

  val source: Source = Source fromURL (getClass getResource "/jobs.txt")
  val jobs: Vector[Job] = JobsFromFile.read(source).toVector

  it should "read 10000 jobs from file" in {
    expectResult(10000)(jobs.length)
  }

  it should "read ru.antonivanov87.jobs.Job(weight=8, length=50) from first line" in {
    expectResult(Job(weight=8, length=50))(jobs(0))
  }

  it should "read ru.antonivanov87.jobs.Job(weight=68, length=15) from last line" in {
    expectResult(Job(weight=68, length=15))(jobs.last)
  }

}
