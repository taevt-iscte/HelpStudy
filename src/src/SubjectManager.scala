

case class SubjectManager(val subjs: List[Subject]) {

  def addSubject(subj: Subject): SubjectManager = SubjectManager.addSubject(this, subj)

}

object SubjectManager {

  def addSubject(subj_man: SubjectManager, subj: Subject): SubjectManager = {
    SubjectManager(subj :: subj_man.subjs)
  }

}
