This folder has git patches that can get applied to the project.

To apply a patch run the command below from the root git folder:

git apply --whitespace=nowarn git_patches/<.patch_file>

Example:

git apply --whitespace=nowarn git_patches/easier-login.patch

To create a new patch, run the command below from this folder:

git format-patch -<n> <commit-number> --stdout > <name_of_patch>.patch

<n> is the number of commits from the base commit to pick up the changes

<commit-number> is the commit number

Example:

git format-patch -1 HEAD --stdout > easier_login.patch