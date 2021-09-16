using System.Collections.Generic;

namespace WebApplicationStudentsMVC.Models
{
    public class StudentViewModel
    {
        
        public List<StudentSubjectViewModel> Subjects { get; set; } = new List<StudentSubjectViewModel>();
    }
}
