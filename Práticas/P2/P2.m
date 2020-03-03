%% Clear
close all;
clear all;
clc;

%% Ex1_a

N = 1e5; % número de experiências 
p = 0.5; % probabilidade de rapaz
k = 1; % número de rapazes
n = 2; % número de filhos

familias = rand(n,N) < p; 
sucessos= sum(familias)>=k;
probSim= sum(sucessos)/N;

%% Ex1_c

familias = rand(2,1e5) < 0.5;       % P(B|A)
A = sum(familias)>=1;
B = sum(familias)==2;
resp = sum(B)/sum(A);

%% Ex1_d
familias = rand(2,1e5) < 0.5;
A = sum(familias)==2;
B = familias(1,:);
resp = sum(A)/sum(B);

%% Ex1_e

familias = rand(5,1e5) < 0.5;       % P(B|A)
A = sum(familias)>=1;
B = sum(familias)==2;
resp = sum(B)/sum(A);

%% Ex1_f

familias = rand(5,1e5) < 0.5;       % P(B|A)
A = sum(familias)>=1;
B = sum(familias)>=2;
resp = sum(B)/sum(A);

%% Ex2_a

N = 1e7; % número de experiências 
p = 0.5; % probabilidade de cara
k = 10; % número de caras
n = 10; % número de lançamentos

lancamentos = rand(n,N) < p; 
sucessos= sum(lancamentos)==k;
probSimA= sum(sucessos)/N;

%% Ex2_b

lancamentos = rand(11,1e7) < 0.5;
A = sum(lancamentos)==11;
B = sum(lancamentos(1:10,:))==10;
resp = sum(A)/sum(B);

%% Ex3

C = 1/1000;    %probabilidade de cancro
nC = 999/1000;

P = (0.9*C) + (0.1*nC); %Probabilidade de positivo

sucesso = (0.9*C)/P; %Probabilidade de ter cancro sabendo que deu positivo

%% Ex4 a/b

N = 1e5; % número de experiências 
fa = 0;  %casos favoraveis a)
fb = 0;  %casos favoraveis b)   

experiencias = randi(100,20,N);
for k=1:N
    norep = unique(experiencias(:,k));  %analisar coluna a coluna e retirar os alvos repetidos
    if length(norep)==20
        fa = fa+1;
    else
        fb = fb+1;
    end
end

Pa = fa/N;    %Probabilidade final a)
Pb = fb/N;    %Probabilidade final b)
    
%% Ex4 c

N = 1e4; % número de experiências
plotcount = 1;
for m=[1000, 1e4, 1e5, 1e6]
    res = zeros(1,10);
    i=1;
    for n=1:10:100
        f = 0;  %casos favoraveis
        experiencias = randi(m,n,N);
        for k = 1:N
            if length(unique(experiencias(:,k)))~=n
                f = f+1;
            end
        end
        res(i) = f/N;
        i = i+1;
    end
    subplot(1,4,plotcount);
    plot(1:10:100, res);
    xlabel('n');
    ylabel('Probabilidade');
    title(['m=' , num2str(m)])
    plotcount = plotcount +1;
end

%% Ex6

N = 1e5; % número de experiências


for n = 40:500
    experiencias = randi(366,n,N);          %366 dias, n pessoas, N experiencias
    f = 0;  %casos favoraveis
    for k=1:N
        norep = unique(experiencias(:,k));  %analisar coluna a coluna e retirar as datas repetidas
        if length(norep)~=n
            f = f+1;
        end
    end
    P = f/N;
    if P > 0.9      %Probabilidade de terem a mesma data de aniversario
        break
    end
end

