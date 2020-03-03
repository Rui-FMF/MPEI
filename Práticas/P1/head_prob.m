function Prob = head_prob(p, n, k, N)

lancamentos = rand(n,N) < p; 
sucessos= sum(lancamentos)==k;
Prob = sum(sucessos)/N;
end