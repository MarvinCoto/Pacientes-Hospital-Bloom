package modelo

data class DataClassPacientes(
    val uuidPaciente: String,
    val uuidMedicamentos: String,
    val uuidEnfermedades: String,
    val uuidHabitacon: String,
    val uuidCama: String,
    var nombre: String,
    val apellido: String,
    val edad: Int,
    val fecha_nacimiento: String,
    val hora_medicamento: String
)
