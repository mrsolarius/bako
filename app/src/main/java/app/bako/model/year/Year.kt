package app.bako.model.year

import app.bako.model.workingday.WorkingDay

class Year(totalWorkingHour:Float) {

    lateinit var workingDay: WorkingDay

    constructor(totalWorkingHour: Float, workingDay: WorkingDay) : this(totalWorkingHour){
        this.workingDay = workingDay;
    }

    fun fetch(year:Int){
        //@Todo
    }
}