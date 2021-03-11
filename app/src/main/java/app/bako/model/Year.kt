package app.bako.model

class Year(totalWorkingHour:Float) {

    lateinit var workingDay: WorkingDay
    var totalWorkingHour: Float = 0.0f

    constructor(totalWorkingHour: Float, workingDay: WorkingDay){
        this.totalWorkingHour = totalWorkingHour
        this.workingDay = workingDay;
    }

    fun fetch(year:Int){
        //@Todo
    }
}