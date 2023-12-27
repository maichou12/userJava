package Main;

import entity.Roles;
import entity.UserDao;
import entity.Users;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final UserDao userDao = new UserDao();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choix;

        do {
            displayMenu();
            choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    addUser();
                    break;
                case 2:
                    displayUsers();
                    break;
                case 0:
                    System.out.println("Fin du programme.");
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        } while (choix != 0);
    }

    private static void displayMenu() {
        System.out.println("1. Ajouter un utilisateur");
        System.out.println("2. Afficher les utilisateurs");
        System.out.println("0. Quitter");
        System.out.print("Choix : ");
    }


    private static void displayRoles() {
        List<Roles> roles = userDao.listRoles();
        System.out.println("Liste des rôles disponibles :");
        for (Roles role : roles) {
            System.out.println(role.getId() + ". " + role.getNom());
        }
    }

    private static Roles findRoleById(int roleId) {
        List<Roles> roles = userDao.listRoles();
        for (Roles role : roles) {
            if (role.getId() == roleId) {
                return role;
            }
        }
        return null; // Rôle non trouvé
    }

    private static void addUser() {
        System.out.print("Email : ");
        String email = scanner.nextLine();

        System.out.print("Mot de passe : ");
        String password = scanner.nextLine();

        displayRoles(); // Afficher la liste des rôles disponibles

        System.out.println("Rôle (entrez l'ID du rôle) : ");
        int roleId = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne après le nombre

        Roles selectedRole = findRoleById(roleId);
        if (selectedRole == null) {
            System.out.println("ID de rôle invalide. Utilisateur non ajouté.");
            return;
        }
        Users user = new Users(0, email, password, selectedRole.getId());
        userDao.addUser(user);
        System.out.println("Utilisateur ajouté avec succès.");
        userDao.listUsers(); // Liste les utilisateurs après l'ajout
        scanner.nextLine();
    }
        private static void displayUsers () {
            List<Users> users = userDao.listUsers();
            if (users.isEmpty()) {
                System.out.println("Aucun utilisateur enregistré.");
                return;
            }
            System.out.println("Liste des utilisateurs :");
            for (Users user : users) {
                System.out.println("ID: " + user.getId());
                System.out.println("Email: " + user.getEmail());
                System.out.println("Mot de passe hashé: " + user.getPasswordHashed());
                System.out.println("Rôle: " + user.getRole().getNom());
                System.out.println("------------");
            }
        }
    }

