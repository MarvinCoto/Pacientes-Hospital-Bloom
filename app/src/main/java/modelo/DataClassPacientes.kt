package modelo

data class DataClassPacientes(
    val uuidPaciente: String,
    val uuidMedicamentos: String,
    val uuidEnfermedades: String,
    val uuidHabitacon: String,
    val uuidCama: String,
    val nombre: String,
    val apellido: String,
    val edad: String,
    val fecha_nacimiento: String,
    val hora_medicamento: String
)
