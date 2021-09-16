using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace WebApplicationStudentsMVC.Models
{
    public class Subject
    {
        public int Id { get; set; }

        public string Name { get; set; }

        public int? ProfessorId { get; set; }

        public Professor Professor { get; set; }

        public List<StudentSubject> StudentSubjects { get; set; }


    }
}
