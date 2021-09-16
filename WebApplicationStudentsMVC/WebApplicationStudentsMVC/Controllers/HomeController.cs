using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Logging;
using System;
using System.Diagnostics;
using System.Linq;
using WebApplicationStudentsMVC.Models;

namespace WebApplicationStudentsMVC.Controllers
{
    public class HomeController : Controller
    {
        private readonly ILogger<HomeController> _logger;
        private readonly StudentServiceDbContext _dbContext;

        public HomeController(ILogger<HomeController> logger, StudentServiceDbContext dbContext)
        {
            _logger = logger;
            _dbContext = dbContext;

        }

        public IActionResult Index()
        {
            return View();
        }

        public IActionResult Login(string user, string password)
        {
            if (String.IsNullOrEmpty(user) && String.IsNullOrEmpty(password))
            {
                return View("Views/Home/Index.cshtml");
            }

            var dbUser = _dbContext.Users.FirstOrDefault(x => x.Username == user && x.Password == password);

            if (dbUser == null)
            {
                return View("Views/Home/Index.cshtml");
            }

            if (dbUser.Role == "student")
            {
                var viewModel = new StudentViewModel
                {
                    Subjects = _dbContext.StudentSubjects.Include(x => x.Subject)
                    .Where(x => x.StudentId == dbUser.StudentId.Value)
                    .Select(x => new StudentSubjectViewModel
                    {
                        Name = x.Subject.Name,
                        Grade = x.Grade
                    }).ToList()
                };
                return View("Views/UserViews/StudentView.cshtml", viewModel);
            }
            else if (dbUser.Role == "professor")
            {
                var viewModel = new ProfessorViewModel
                {
                    LoggedInProfessorId = dbUser.ProfessorId.ToString(),
                    ProfessorSubjects = _dbContext.Subjects.Where(x => x.ProfessorId == dbUser.ProfessorId.Value).ToList()
                };

                return View("Views/UserViews/ProfessorView.cshtml", viewModel);
            }
            else if (dbUser.Role == "studentservice")
            {

                var viewModel = new StudentServiceViewModel
                {
                    Professors = _dbContext.Professors.ToList(),
                    FreeSubjects = _dbContext.Subjects.Where(x => !x.ProfessorId.HasValue).ToList(),
                    Subjects = _dbContext.Subjects.ToList()
                };

                return View("Views/UserViews/StudentsServiceView.cshtml", viewModel);
            }
            else
            {
                return View("Views/Home/Index.cshtml");
            }


        }

        public IActionResult Privacy()
        {
            return View();
        }

        [ResponseCache(Duration = 0, Location = ResponseCacheLocation.None, NoStore = true)]
        public IActionResult Error()
        {
            return View(new ErrorViewModel { RequestId = Activity.Current?.Id ?? HttpContext.TraceIdentifier });
        }
    }
}
