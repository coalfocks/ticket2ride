# ticket2ride
Ticket to Ride

Clone this repository to your computer so you can work on it locally. We'll consider the master branch to be the production branch. This means the only changes submitted to the master branch should be ones that we know work. Branches are just ways to keep code separate when you are working on different things. You make a new branch from the master branch, then make all your changes on the new branch. When you are done and you know it works, you submit a pull request to merge the branch you created into the master branch. This link has an explanation of how pull requests work https://yangsu.github.io/pull-request-tutorial/. If at any point you get weird permission things from git or it says it can't find some upstream repo or something, let me know.

# Clone repo

1) Navigate to the directory where you want the project via Terminal (let me know if you need help doing that). type in:
 git clone https://github.com/coalfocks/ticket2ride.git
 and it will create the repo on your machine

# How to do Pull Requests

1) When you are ready to start working on something, update Your local repo.

git pull origin master

2) Create/Checkout a new local branch with a name that describes what you are doing. Push your branch to the GitHub server so you can sync stuff there. Replace branch-name with whatever you want to name your branch. the -b after checkout means it creates a new branch. Git push origin branch-name means you push the new branch you created to the origin (GitHub server).

git checkout -b branch-name
git push origin branch-name

3) Work on this branch locally like you normally would and sync changes to it to the GitHub server. Whenever a change is made to the master branch, run “git pull origin master” to avoid merge conflicts down the road. When I say work on this branch normally, I mean do the usual git add, git commit, and git push stuff but use the git push below instead of origin master.

Push your local changes on your branch to the GitHub server on your branch.

git add .
git commit  (You'll have to type in a message explaining what you did by typing "i", type the message, then ESC, ":", "wq", enter
git push origin branch-name

Check what branch you are on/what you’ve done.

git status

Switch between branches.

git checkout branch-name

When you are ready to submit a pull request, do it through the GitHub web repo page, it's the button that says "New pull request". The web thing is pretty straightforward after you have done it once, so if you start it and it doesn't make sense, I'll walk you through it.

# GITHUB DESKTOP GLIENT
  Alternatively, you can download the Github Desktop Client and just do all of that through a GUI if you aren't comfortable with command line stuff
