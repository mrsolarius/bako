package app.bako.model.workingday

import app.bako.model.herit.AssignmentCode
import app.bako.model.realworking.RealWorking
import java.util.*

class WorkingDay(var date: Date) {

    lateinit var assignmentCode: AssignmentCode
    lateinit var realWorking: RealWorking

    constructor(date: Date, realWorking: RealWorking) : this(date){
        this.realWorking = realWorking
    }

    constructor(date: Date, assignmentCode: AssignmentCode) : this(date){
        this.assignmentCode = assignmentCode
    }

    constructor(date: Date, realWorking: RealWorking, assignmentCode: AssignmentCode) : this(date){
        this.date = date
        this.realWorking = realWorking
        this.assignmentCode = assignmentCode
    }
}