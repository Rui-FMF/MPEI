%% Clear
close all;
clear all;
clc;

%% Ex1

% Código 1 Fórmula
p = 0.5; 
k = 2; % numero de caras
n = 3; % numero de lançamentos
prob = factorial(n)/(factorial(n-k)*factorial(k))*p^k*(1-p)^(n-k);

% Código 2 Simulação v1
%experiencias = rand(3,10000);
%lancamentos = experiencias > 0.5;
%resultados= sum(lancamentos);
%sucessos= resultados==2;
%probSim= sum(sucessos)/10000;

% Código 2 Simulação v2
N = 1e5; %número de experiências 
p = 0.5; %probabilidade de cara 
k = 2; %número de caras 
n = 3; %número de lançamentos

lancamentos = rand(n,N) < p; 
sucessos= sum(lancamentos)==k;
probSim= sum(sucessos)/N;

%% Ex4

Y = zeros(0,20);

for x = 0:20
    Y(x+1) = head_prob(0.5, 20, x, 10000);
end

stem(0:20,Y);

%% Ex5

p = 0.3; 
k = 3; % numero de torneiras com defeito
n = 5; % tamanho da amostra do controlo de qualidade
prob = factorial(n)/(factorial(n-k)*factorial(k))*p^k*(1-p)^(n-k);

N = 1e5; % número de experiências 
p = 0.3; % probabilidade de defeito
k = 3; % número de torneiras com defeito
n = 5; % tamanho da amostra do controlo de qualidade

amostras = rand(n,N) < p; 
sucessos = sum(amostras)==k;
probSim = sum(sucessos)/N;

