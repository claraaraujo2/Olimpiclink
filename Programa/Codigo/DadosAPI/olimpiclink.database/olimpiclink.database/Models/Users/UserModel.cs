﻿using System;
using System.ComponentModel.DataAnnotations;

namespace olimpiclink.database.Models.Users
{
    public class UserModel
    {
        [Key]
        public int id_user { get; set; }
        public string name_user { get; set; }
        public string email_user { get; set; }
        public string password_user { get; set; }
        public string login_user { get; set; }
        public string profile_picture_user { get; set; }
        public string created_at_user { get; set; }
        public string updated_at_user { get; set; }

        public UserModel
            (string name_user, string email_user, string password_user, string login_user) 
        {
            this.name_user = name_user;
            this.email_user = email_user;
            this.password_user = password_user;
            this.login_user = login_user;
            created_at_user = DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss");
            updated_at_user = DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss");
        }

    }
}