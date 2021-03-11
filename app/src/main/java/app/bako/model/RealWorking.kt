package app.bako.model

class RealWorking(var realEndDate: String) {
    lateinit var assignmentCode: AssignmentCode

    constructor(realEndDate: String, assignmentCode: AssignmentCode){
        this.realEndDate = realEndDate
        this.assignmentCode = assignmentCode
    }
}