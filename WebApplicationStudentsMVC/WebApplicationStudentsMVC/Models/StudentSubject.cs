using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace WebApplicationStudentsMVC.Models
{
    public class StudentSubject
    {
        public int Id { get; set; }

        public int? Grade { get; set; }

        public int StudentId { get; set; }

        public Student Student { get; set; }

        public int SubjectId { get; set; }

        public Subject Subject { get; set; }

        public int? ProfessorId { get; set; }

        public Professor Professor { get; set; }




    }
}
