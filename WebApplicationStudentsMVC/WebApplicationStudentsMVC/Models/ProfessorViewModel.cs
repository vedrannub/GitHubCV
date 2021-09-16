using System.Collections.Generic;

namespace WebApplicationStudentsMVC.Models
{
    public class ProfessorViewModel
    {
        public string LoggedInProfessorId { get; set; }
        public string SelectedSubjectId { get; set; }
        public List<Subject> ProfessorSubjects { get; set; } = new List<Subject>();
        public List<StudentSubjectViewModel> SubjectStudents { get; set; } = new List<StudentSubjectViewModel>();
        public List<Student> StudentsForAddingGrade { get; set; } = new List<Student>();
        public string SelectedStudentsForAddingGradeId { get; set; }
        public string SelectedStudentsGradeToSave { get; set; }
    }
}
