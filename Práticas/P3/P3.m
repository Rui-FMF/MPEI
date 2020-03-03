%% Clear
close all;
clear all;
clc;

%% Ex1

pX = ones(1,6)/6;   %probabilidades 1/6 para todos
xi = 1:6;           %lados do dado
subplot(1,2,1);
stem(xi, pX);
ylabel('pX');
xlabel('xi');

subplot(1,2,2);
FX = cumsum([0 0 pX 0 0]);  %Distribuição acumulada
stairs(-1:8, FX);


%% Ex2

%% a)
sx = 1:100;             %Espaço de amostragem
pX = ones(1,100)/100;   %Probabilidade de cada nota

%% b)

sx = [5, 50, 100];                %Espaço de amostragem
pX = [0.90, 0.09, 0.01];          %Probabilidades

stem(sx, pX);                     %Massa de probabilidade
ylabel('pX');
xlabel('xi');
axis([0, 101, 0, 1]);


%% Ex3

N = 1e5;    %Num experiencias
p = 0.5;    %prob de cara
n = 4;      %num lançamentos

xi = 0:4;
pX = zeros(1,5);
lancamentos = rand(n,N) <= p;

for k=0:4
   sucesso = sum(lancamentos)==k;
   pX(k+1) = sum(sucesso)/N;
end

stem(xi, pX);                     %Massa de probabilidade
ylabel('pX');
xlabel('xi');
axis([0, 5, 0, 1]);

EX = sum(xi.*pX);   %valor esperado

EX2 = sum((xi.^2).*pX);

VarX = EX2 - (EX.^2);   %Variancia

dp = sqrt(VarX); %Desvio Padrão

%% Ex4

N = 1e5;
p = 0.3;
n = 5; %num de peças

xi = 0:5;
pX = zeros(1,5);
amostras = rand(n,N) <= p;

for k=0:5
    sucesso = sum(amostras)==k;
    pX(k+1) = sum(sucesso)/N;
end

FX = cumsum([0 pX 0]);
stairs(-1:6, FX);   %FALTA HISTOGRAMA

Prob = sum(sum(amostras)<=2)/N;

%% Ex6

p = 1/1000;
n = 8000;
k = 7;
              %factorial(n)/(factorial(k)*factorial(n-k)) ==
              %nchoosek(n,k)
bin = nchoosek(n,k)*(p^k)*(1-p)^(n-k);
alfa = n*p;
poi = ((alfa^k)/factorial(k))*exp(-alfa);

%% Ex7

alfa = 15;
k = 0;

prob_a = ((alfa^k)/factorial(k))*exp(-alfa);

prob = 0;

for k=0:10
    prob = prob + ((alfa^k)/factorial(k))*exp(-alfa);
end

prob_b = 1-prob;

%% Ex8

x = 1:4;
fx = (x+5)/30;

if min(fx)>=0 & max(fx) & sum(fx)==1
    fprintf('Sim\n');
else
    fprintf('Nao\n');
end

%% Ex9

alfa = 1;
k = 0;

prob_0 = ((alfa^k)/factorial(k))*exp(-alfa);
prob = 1-prob_0;

%% Ex10

P =(3-0)/(10-0);

sim = 10*rand(1,1e6);
r_sim = sum(sim<3)/1e6;


