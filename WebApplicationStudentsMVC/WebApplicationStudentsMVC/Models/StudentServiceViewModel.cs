using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace WebApplicationStudentsMVC.Models
{
    public class StudentServiceViewModel
    {
        public List<Professor> Professors { get; set; } = new List<Professor>();

        public List<Subject> FreeSubjects { get; set; } = new List<Subject>();

        public string SelectedProfessorId { get; set; }

        public string SelectedProfessorSubjectId { get; set; }

        public List<Subject> SelectedProfessorSubjects { get; set; } = new List<Subject>();

        //for students

        public string SelectedSubjectId { get; set; }

        public List<Subject> Subjects { get; set; } = new List<Subject>();

        public List<Student> SelectedSubjectStudents { get; set; } = new List<Student>();
        public List<Student> SelectedSubjectNotAssingedStudents { get; set; } = new List<Student>();
        public string SelectedSubjectStudentId { get; set; }


    }
}
