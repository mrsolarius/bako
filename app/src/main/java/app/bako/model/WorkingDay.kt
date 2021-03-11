package app.bako.model

import java.util.*

class WorkingDay(var date: Date) {

    lateinit var assignmentCode: AssignmentCode
    lateinit var realWorking: RealWorking

    constructor(date: Date, realWorking: RealWorking){
        this.date = date
        this.realWorking = realWorking
    }

    constructor(date: Date, assignmentCode: AssignmentCode){
        this.date = date
        this.assignmentCode = assignmentCode
    }

    constructor(date: Date, realWorking: RealWorking, assignmentCode: AssignmentCode){
        this.date = date
        this.realWorking = realWorking
        this.assignmentCode = assignmentCode
    }
}