package app.bako.model.realworking

import app.bako.model.herit.AssignmentCode

class RealWorking(var realEndDate: String) {
    lateinit var assignmentCode: AssignmentCode

    constructor(realEndDate: String, assignmentCode: AssignmentCode) : this(realEndDate){
        this.assignmentCode = assignmentCode
    }
}