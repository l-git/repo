#include <stdio.h>
struct str{
int i;
char ch[0];
};

struct foo{
struct str *a;
};

int main(int argc,char **argv){
struct foo f={0};

if(f.a -> ch){
printf(f.a->ch);
}

return 0;
}