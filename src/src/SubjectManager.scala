

case class SubjectManager(val subjs: List[Subject]) {

  def addSubject(subj: Subject): SubjectManager = SubjectManager.addSubject(this, subj)

  def sort_Subject(): SubjectManager = SubjectManager.sort_Subject(this)

}

object SubjectManager {

  def addSubject(subj_man: SubjectManager, subj: Subject): SubjectManager = {
    SubjectManager(subj :: subj_man.subjs)
  }

  def sort_Subject(subj_man: SubjectManager): SubjectManager = {
    SubjectManager(subj_man.subjs.sortBy(_.name))
  }

}
