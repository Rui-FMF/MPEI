%% Clear
close all;
clear all;
clc;

%% Ex1

%a/b)

z = lcg(4,9,5,23,1000);
uz = unique(z);
n = length(uz);
mz = z/23;
histogram(mz);

%c)
znag = lcg(4,7^5,0,2^31-1,1000);
uznag = unique(znag);

%% Ex2

%a)
p = 0.35;
experiencias = rand(1,10)<=p;

%b)
dado = ceil(rand(1,15)*6);

%c)
conjunto = 14*rand(1,20)-4;


%% Ex3

 hist(Bernoulli(.3, 10000),(0:1)');
 
%% Ex4

N=1e6;
n=20;
p=0.3; 
X=Binomial(n,p, N);
hist(X);

%% Ex5
xi = [1,2,3,4,5,6];
pX = [1/20,1/20,1/20,1/20,1/20,15/20];
X = fmp(xi,pX,1e4);
hist(X,xi);

%% Ex6
hist(exponencial(0.25,1e5),20)

%% Ex7
m = 14;
var = 2;
N = 1e6;
[X,Y] = BoxMuller(N);
subplot(1,2,1);
hist((X*sqrt(var))+m,50);
subplot(1,2,2);
Z = (randn(1,N)*var)+m;
hist(Z,50);

%% Ex8
N = 1e4;
a = -5;
b = 5;

[X,Y] = Rejeicao(a,b,N);
plot(X,Y);


%% Ex9

totoloto = SimUrna(5,49);
num_da_sorte = SimUrna(1,13);

